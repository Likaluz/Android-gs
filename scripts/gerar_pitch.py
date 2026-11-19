"""Gera o documento de apoio ao pitch a partir do Markdown e prints reais.

Requer reportlab. Execute na raiz do projeto: python scripts/gerar_pitch.py
"""
from pathlib import Path
import html
import re

from reportlab.lib import colors
from reportlab.lib.enums import TA_LEFT
from reportlab.lib.pagesizes import A4
from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.pdfbase import pdfmetrics
from reportlab.pdfbase.ttfonts import TTFont
from reportlab.platypus import (
    SimpleDocTemplate, Paragraph, Spacer, Table, TableStyle,
    PageBreak, Image, Preformatted, KeepTogether,
)

ROOT = Path(__file__).resolve().parents[1]
FONTS = Path('C:/Windows/Fonts')
if (FONTS / 'segoeui.ttf').exists():
    pdfmetrics.registerFont(TTFont('Corpo', str(FONTS / 'segoeui.ttf')))
    pdfmetrics.registerFont(TTFont('Negrito', str(FONTS / 'segoeuib.ttf')))
    pdfmetrics.registerFontFamily('Corpo', normal='Corpo', bold='Negrito')
    regular, bold = 'Corpo', 'Negrito'
else:
    regular, bold = 'Helvetica', 'Helvetica-Bold'

styles = getSampleStyleSheet()
for key in ('Normal', 'BodyText'):
    styles[key].fontName = regular
    styles[key].fontSize = 10
    styles[key].leading = 15
    styles[key].spaceAfter = 9
styles['Title'].fontName = bold
styles['Title'].fontSize = 25
styles['Title'].leading = 31
styles['Title'].textColor = colors.HexColor('#205A43')
styles['Title'].alignment = TA_LEFT
styles['Heading2'].fontName = bold
styles['Heading2'].fontSize = 14
styles['Heading2'].leading = 19
styles['Heading2'].textColor = colors.HexColor('#205A43')
styles['Heading2'].spaceBefore = 14
styles['Heading2'].spaceAfter = 8
styles['Heading2'].keepWithNext = True
styles.add(ParagraphStyle('Celula', parent=styles['BodyText'], fontSize=8, leading=11, spaceAfter=0))
styles.add(ParagraphStyle('Legenda', parent=styles['BodyText'], fontSize=9, leading=12))


def inline(text):
    text = text.replace('\u2014', '-').replace('\u2013', '-').replace('\u2011', '-')
    text = html.escape(text)
    text = re.sub(r'\[([^\]]+)\]\((https?://[^)]+)\)', r'<link href="\2" color="#205A43">\1</link>', text)
    text = re.sub(r'\*\*(.+?)\*\*', r'<b>\1</b>', text)
    text = re.sub(r'`([^`]+)`', r'\1', text)
    return text


def footer(canvas, doc):
    canvas.saveState()
    canvas.setFont(regular, 8)
    canvas.setFillColor(colors.HexColor('#536154'))
    canvas.drawString(42, 24, 'Bairro Vivo | Lucas Domingues | RM 553304 | Global Solution 2026')
    canvas.drawRightString(A4[0] - 42, 24, str(doc.page))
    canvas.restoreState()


story = []
lines = (ROOT / 'docs/PITCH.md').read_text(encoding='utf-8').splitlines()
i = 0
while i < len(lines):
    line = lines[i].strip()
    if not line:
        i += 1
        continue
    if line.startswith('```'):
        code = []
        i += 1
        while i < len(lines) and not lines[i].startswith('```'):
            code.append(lines[i])
            i += 1
        story.append(Preformatted('\n'.join(code), ParagraphStyle('Codigo', fontName=regular, fontSize=8, leading=12)))
    elif line.startswith('|'):
        rows = []
        while i < len(lines) and lines[i].strip().startswith('|'):
            cells = lines[i].strip().strip('|').split('|')
            if not all(re.fullmatch(r'\s*:?-+:?\s*', c) for c in cells):
                rows.append([Paragraph(inline(c.strip()), styles['Celula']) for c in cells])
            i += 1
        widths = [65, 446] if len(rows[0]) == 2 else [100, 202, 209]
        table = Table(rows, colWidths=widths, repeatRows=1, hAlign='LEFT')
        table.setStyle(TableStyle([
            ('BACKGROUND', (0, 0), (-1, 0), colors.HexColor('#DCECCE')),
            ('VALIGN', (0, 0), (-1, -1), 'TOP'),
            ('LINEBELOW', (0, 0), (-1, -1), 0.3, colors.HexColor('#CFD8CC')),
            ('LEFTPADDING', (0, 0), (-1, -1), 8), ('RIGHTPADDING', (0, 0), (-1, -1), 8),
            ('TOPPADDING', (0, 0), (-1, -1), 8), ('BOTTOMPADDING', (0, 0), (-1, -1), 8),
        ]))
        story.extend([table, Spacer(1, 10)])
        continue
    elif line.startswith('# '):
        story.append(Paragraph(inline(line[2:]), styles['Title']))
    elif line.startswith('## '):
        story.append(Paragraph(inline(line[3:]), styles['Heading2']))
    else:
        story.append(Paragraph(inline(line), styles['BodyText']))
    i += 1

story.append(PageBreak())
story.append(Paragraph('Capturas da aplicação', styles['Title']))
story.append(Paragraph('Telas reais do MVP em emulador Android. Dados fictícios para demonstração acadêmica.', styles['BodyText']))
for pair in [ [('01-inicio', 'Início e destaques'), ('03-filtro', 'Filtro de alimentação')],
              [('04-detalhes', 'Detalhes da iniciativa'), ('05-sem-resultados', 'Busca sem resultados')] ]:
    cells = []
    for filename, label in pair:
        path = ROOT / 'docs/screenshots' / f'{filename}.png'
        if not path.exists():
            raise FileNotFoundError(path)
        image = Image(str(path), width=194, height=431)
        cells.append([Paragraph(label, styles['Heading2']), image])
    story.append(Table([[cells[0], cells[1]]], colWidths=[255, 256], style=[('VALIGN', (0, 0), (-1, -1), 'TOP')]))
    if pair[0][0] == '01-inicio':
        story.append(PageBreak())

out = ROOT / 'docs/Pitch-Bairro-Vivo.pdf'
SimpleDocTemplate(str(out), pagesize=A4, leftMargin=42, rightMargin=42,
                  topMargin=40, bottomMargin=42, title='Bairro Vivo - documento de pitch',
                  author='Lucas Domingues - RM 553304').build(story, onFirstPage=footer, onLaterPages=footer)
print(out)
